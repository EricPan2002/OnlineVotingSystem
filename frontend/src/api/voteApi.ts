import axios from "axios";

const api = axios.create({
    baseURL: 'http://localhost:8080/api'
});

// define data type (must correspond exactly to VoteItem from Entity)
export interface VoteItem {
  id: number;
  name: string;
  voteCount: number;
}

// get vote items
export const getItems = () => api.get<VoteItem[]>('/items');

// vote (send name and ID list to backend)
export const postVote = (voterName: string, itemIds: number[]) => 
  api.post('/vote', { voterName, itemIds });

export const addItem = (name: string) => 
  axios.post('http://localhost:8080/api/items', { name });

export const deleteItem = (id: number) => 
  axios.delete(`http://localhost:8080/api/items/${id}`);