import { createRouter, createWebHistory } from 'vue-router';
import VoteView from '../views/VoteView.vue';
import AdminView from '../views/AdminView.vue';

const routes = [
  { path: '/', component: VoteView }, // voting page
  { path: '/admin', component: AdminView } // admin page
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;