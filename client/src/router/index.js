import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Register from '@/components/Register'
import Login from '@/components/Login'
import Eventos from '@/components/Eventos'
import MeusEventos from '@/components/MeusEventos'
import MinhasInscricoes from '@/components/MinhasInscricoes'
// import Banks2 from '@/components/Banks2'
import Perfil from '@/components/Perfil'
import BankView from '@/components/BankView'
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
      path: '/eventos',
      name: 'eventos',
      component: Eventos
    },
    {
      path: '/meuseventos',
      name: 'meuseventos',
      component: MeusEventos
    },
    {
      path: '/minhasinscricoes',
      name: 'minhasinscricoes',
      component: MinhasInscricoes
    },
    {
      path: '/perfil',
      name: 'perfil',
      component: Perfil
    },
    {
      path: '/banks/:bankId',
      name: 'bank',
      component: BankView
    }
  ]
})
