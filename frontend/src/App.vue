<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { getItems, postVote, type VoteItem } from './api/voteApi';
import { addItem, deleteItem } from './api/voteApi';

const items = ref<(VoteItem & { voteCount?: number })[]>([]);
const voterName = ref('');
const selectedIds = ref<number[]>([]);
const newItemName = ref(''); // new input box

const fetchItems = async () => {
  try {
    const res = await getItems();
    items.value = res.data;
  } catch (error) {
    console.error('抓取失敗', error);
  }
};

onMounted(fetchItems);

// vote
const submitVote = async () => {
  if (!voterName.value || selectedIds.value.length === 0) {
    alert('請填寫姓名並選擇至少一個項目!');
    return;
  }
  try {
    await postVote(voterName.value, selectedIds.value);
    alert('投票成功！');
    voterName.value = '';
    selectedIds.value = [];
    fetchItems();
  } catch (error) {
    alert('投票失敗，請檢查後端是否啟動');
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
</script>

<template>
  <div class="vote-app">
    <h1>線上投票系統</h1>
    
    <div class="form-item">
      <label>您的姓名：</label>
      <input v-model="voterName" type="text" placeholder="輸入姓名" />
    </div>

    <div class="items-list">
      <h3>投票項目（多選）：</h3>
      <div v-for="item in items" :key="item.id" class="checkbox-group">
        <input 
          type="checkbox" 
          :id="'item-' + item.id" 
          :value="item.id" 
          v-model="selectedIds" 
        />
        <label :for="'item-' + item.id">
          {{ item.name }}
          <span class="vote-tag">({{ item.voteCount }} 票)</span>
        </label>
      </div>
    </div>

    <button @click="submitVote" class="submit-btn">確認送出</button>
  </div>

  <hr />
<h2>後台管理</h2>
<div>
  <input v-model="newItemName" placeholder="新項目名稱" />
  <button @click="handleAdd">新增項目</button>
</div>

<div v-for="item in items" :key="item.id">
  {{ item.name }} 
  <button @click="handleDelete(item.id)">刪除</button>
</div>
</template>

<style scoped>
.vote-app { max-width: 500px; margin: 2rem auto; padding: 2rem; border: 1px solid #ddd; border-radius: 8px; }
.form-item { margin-bottom: 1.5rem; }
.checkbox-group { margin: 0.5rem 0; }
.submit-btn { 
  margin-top: 1.5rem; width: 100%; padding: 10px; 
  background-color: #007f6e; color: white; border: none; cursor: pointer; 
}
</style>