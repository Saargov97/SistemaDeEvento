import Api from '@/services/Api'

export default {
  post (inscricao) {
    return Api().post('inscricao', inscricao)
  },
  user (userId) {
    return Api().get(`inscricaoUsuario/${userId}`)
  },
  delete (inscricaoId) {
    return Api().delete(`inscricao/${inscricaoId}`)
  }
}
