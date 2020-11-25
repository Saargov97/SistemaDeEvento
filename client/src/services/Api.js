import axios from 'axios'

export default () => {
  return axios.create({
    baseURL: 'http://177.44.248.1:8081/'
  })
}
