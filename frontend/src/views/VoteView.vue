<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { getItems, postVote, type VoteItem } from '../api/voteApi';

const items = ref<(VoteItem & { voteCount?: number })[]>([]);
const voterName = ref('');
const selectedIds = ref<number[]>([]);


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

</script>

<template>
  <div class="vote-app">
    
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
</template>
