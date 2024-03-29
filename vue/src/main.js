import { createApp } from 'vue'
import CapstoneApp from './App.vue'
import { createStore } from './store'
import router from './router'
import axios from 'axios'
import PrimeVue from 'primevue/config';
import 'primevue/resources/themes/soho-dark/theme.css';
import 'primevue/resources/primevue.min.css';
import 'primeicons/primeicons.css';
import { DeepChat } from 'deep-chat';
import InputText from "primevue/inputtext";
import Button from 'primevue/button';
import Password from 'primevue/password';
import VueDeckgl from "vue-deck.gl";


/* sets the base url for server API communication with axios */
axios.defaults.baseURL = import.meta.env.VITE_REMOTE_API;

/*
 * The authorization header is set for axios when you login but what happens when 
 * you come back or the page is refreshed. When that happens you need to check 
 * for the token in local storage and if it exists you should set the header 
 * so that it will be attached to each request.
 */
let currentToken = localStorage.getItem('token');
let currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken) {
  // Set token axios requests
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

// Create the Vuex store passing in the stored credentials
const store = createStore(currentToken, currentUser);

const app = createApp(CapstoneApp);
app.component('Button', Button);
app.component('Password', Password);
app.component('InputText', InputText);
app.component('DeepChat', DeepChat)
app.use(PrimeVue);
app.use(store);
app.use(router);
app.use(VueDeckgl);
app.mount('#app');
