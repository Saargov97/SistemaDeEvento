import Api from '@/services/Api'

export default {
  index () {
    return Api().get('eventos')
  },
  post (evento) {
    return Api().post('eventos', evento)
  },
  show (eventoId) {
    return Api().get(`eventos/${eventoId}`)
  },
  put (evento) {
    return Api().put(`eventos/${evento.id}`, evento)
  },
  delete (eventoId) {
    return Api().delete(`eventos/${eventoId}`)
  },
  user (userId) {
    return Api().get(`eventosUsuario/${userId}`)
  }
}
