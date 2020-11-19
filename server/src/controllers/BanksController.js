const { Bank } = require('../models')


module.exports = {
  async index (req, res) {
    try {
      const banks = await Bank.findAll({
        limit: 50
      })
      res.send(banks)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao buscar a lista de bancos'
      })
    }
  },
  async post (req, res) {
    try {
      const bank = await Bank.create(req.body)
      res.send(bank)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao salvar banco'
      })
    }
  },
  async put (req, res) {
    try {
      const bank = await Bank.update(req.body, {
        where: {
          id: req.params.bankId
        }
      })
      res.send(bank)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao salvar banco'
      })
    }
  },
  async delete (req, res) {
    try {
      console.log('chegou delete', req)
      await Bank.destroy({
        where: {
          id: req.params.bankId
        }
      })
      console.log('deletou')
      res.send('')
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao deletar banco'
      })
    }
  },
  async show (req, res) {
    try {
      const bank = await Bank.findOne({
        where: {
          id: req.params.bankId
        }
      })
      res.send(bank)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao buscar o banco'
      })
    }
  }
}
