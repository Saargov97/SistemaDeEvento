const { Certificado } = require('../models')


module.exports = {
  async index (req, res) {
    try {
      const certificados = await Certificado.findAll({
        limit: 50
      })
      res.send(certificados)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao buscar a lista de certificados'
      })
    }
  },
  async getUser (req, res) {
    try {
      const certificados = await Certificado.findAll({
        where: {
          id: req.params.userId
        },
        limit: 50
      })
      res.send(certificados)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao buscar a lista de certificados'
      })
    }
  },
  async post (req, res) {
    try {
      const certificado = await Certificado.create(req.body)
      res.send(certificado)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao salvar certificado'
      })
    }
  },
  async put (req, res) {
    try {
      const certificado = await Certificado.update(req.body, {
        where: {
          id: req.params.certificadoId
        }
      })
      res.send(certificado)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao salvar certificado'
      })
    }
  },
  async delete (req, res) {
    try {
      await Certificado.destroy({
        where: {
          id: req.params.certificadoId
        }
      })
      res.send('')
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao deletar certificado'
      })
    }
  },
  async show (req, res) {
    try {
      const certificado = await Certificado.findOne({
        where: {
          id: req.params.certificadoId
        }
      })
      res.send(certificado)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao buscar o certificado'
      })
    }
  }
}
