const axios = require('axios')

axios.create({baseURL: 'http://localhost:3001/'})

async function gerar (inscricaoId, req) {
  console.log('gerar veio')
  return await axios.post(`GerarCertificado/${inscricaoId}`, req)
    .then(response => console.log(response))
    .catch(error => console.log(error));  
}

function teste () {
  return "2"
}

module.export = { gerar: gerar, teste: teste }   