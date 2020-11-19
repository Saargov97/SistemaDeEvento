import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Register from '@/components/Register'
import Login from '@/components/Login'
import Banks from '@/components/Banks'
// import Banks2 from '@/components/Banks2'
import CreateBank from '@/components/CreateBank'
import BankView from '@/components/BankView'
import Crud from '@/components/Crud'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'root',
      component: HelloWorld
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/banks',
      name: 'banks',
      component: Banks
    },
    {
      path: '/banks/create',
      name: 'banks-create',
      component: CreateBank
    },
    {
      path: '/banks/:bankId',
      name: 'bank',
      component: BankView
    },
    {
      path: '/crus',
      name: 'crud',
      component: Crud
    }
  ]
})
