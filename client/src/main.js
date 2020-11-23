// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import { sync } from 'vuex-router-sync'
import store from '@/store/store'
import moment from 'moment'
// import colors from 'vuetify/lib/util/colors'

Vue.config.productionTip = false

Vue.use(Vuetify)

Vue.filter('formatDate', function (value) {
  if (value) {
    return moment(String(value)).format('DD/MM/YYYY hh:mm')
  }
})

Vue.filter('toCurrency', function (value) {
  if (typeof value !== 'number') {
    return value
  }
  var formatter = new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL',
    minimumFractionDigits: 2
  })
  return formatter.format(value)
})

const opts = {}

export default new Vuetify(opts)

sync(store, router)

/* eslint-disable no-new */
new Vue({
  vuetify: new Vuetify(),
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
