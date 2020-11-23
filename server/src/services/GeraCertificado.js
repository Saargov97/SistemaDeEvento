const axios = require('axios')

axios.create({ baseURL: 'http://localhost:3001/' })

function gerar(inscricaoId, req) {
    console.log('gerar veio')
    /*return await axios.post(`GerarCertificado/${inscricaoId}`, req)
      .then(response => console.log(response))
      .catch(error => console.log(error));  */
}


module.export = {
    async teste (req, res) {
        try {
            res.send(100)
        } catch (err) {
            res.status(500).send({
                error: 'Ocorreu um erro ao buscar teste'
            })
        }
    }
}   