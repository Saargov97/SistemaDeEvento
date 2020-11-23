const GeraCertificado = require('../services/GeraCertificado')

function post(req){
  return GeraCertificado().create().post('GerarCertificado', req)
}

module.export = { post };