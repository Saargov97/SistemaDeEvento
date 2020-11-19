import Api from '@/services/Api'

export default {
  index () {
    return Api().get('banks')
  },
  post (bank) {
    return Api().post('banks', bank)
  },
  show (bankId) {
    return Api().get(`banks/${bankId}`)
  },
  put (bank) {
    return Api().put(`banks/${bank.id}`, bank)
  },
  delete (bankId) {
    return Api().delete(`banks/${bankId}`)
  }
}
