<script setup lang="ts">
import { ref, onMounted} from 'vue';
import { getItems, type VoteItem } from '../api/voteApi';
import { addItem, deleteItem } from '../api/voteApi';

const newItemName = ref(''); // new input box
const items = ref<(VoteItem & { voteCount?: number })[]>([]);

const fetchItems = async () => {
  try {
    const res = await getItems();
    items.value = res.data;
  } catch (error) {
    console.error('抓取失敗', error);
  }
};

const handleAdd = async () => {
  if (!newItemName.value) return;
  try {
    await addItem(newItemName.value);
    newItemName.value = '';
    fetchItems(); // refresh
  } catch (error) {
    alert('新增失敗');
  }
};

const handleDelete = async (id: number) => {
  if (!confirm('確定要刪除嗎？')) return;
  try {
    await deleteItem(id);
    fetchItems(); 
  } catch (error) {
    alert('刪除失敗');
  }
};
onMounted(fetchItems);
</script>

<template>
<h2>新增項目</h2>
<div>
  <input v-model="newItemName" type="text" placeholder="新項目名稱" />
  <button class="add-btn" @click="handleAdd">新增項目</button>
</div>

<h2>已有項目</h2>
<div v-for="item in items" :key="item.id">
  {{ item.name }} 
  <button class="delete-btn" @click="handleDelete(item.id)">刪除</button>
</div>
</template>
