const { Inscricao } = require('../models')
const { User } = require('../models')
const { Evento } = require('../models')
const bcrypt = require('bcrypt')
const md5 = require('md5');
const GeraCertificado = require('../services/GeraCertificado')

function hashCertificado(inscricaoId, options) {
  //const saltRounds = 10;

  //const hash = bcrypt.hashSync(`${inscricaoId}`, saltRounds);
  //console.log(hash)
  return md5(`${inscricaoId}`);
}

module.exports = {
  async index(req, res) {
    try {
      const inscricoes = await Inscricao.findAll({
        limit: 50
      })
      res.send(inscricoes)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao buscar a lista de inscrições'
      })
    }
  },
  async getOwner(req, res) {
    try {
      User.hasMany(Inscricao, { foreignKey: 'userId' })
      Inscricao.belongsTo(User, { foreignKey: 'userId' })
      Evento.hasMany(Inscricao, { foreignKey: 'eventoId', where: { userId: req.params.userId } })
      Inscricao.belongsTo(Evento, { foreignKey: 'eventoId' })
      const inscricoes = await Inscricao.findAll({
        include: [Evento, User]
      })
      res.send(inscricoes)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao buscar a lista de inscrições'
      })
    }
  },
  async getUser(req, res) {
    try {
      User.hasMany(Inscricao, { foreignKey: 'userId' })
      Inscricao.belongsTo(User, { foreignKey: 'userId' })
      Evento.hasMany(Inscricao, { foreignKey: 'eventoId' })
      Inscricao.belongsTo(Evento, { foreignKey: 'eventoId' })
      const inscricoes = await Inscricao.findAll({
        where: {
          userId: req.params.userId
        }, include: [Evento, User]
      })
      res.send(inscricoes)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao buscar a lista de inscrições'
      })
    }
  },
  async getEvento(req, res) {
    try {
      User.hasMany(Inscricao, { foreignKey: 'userId' })
      Inscricao.belongsTo(User, { foreignKey: 'userId' })
      Evento.hasMany(Inscricao, { foreignKey: 'eventoId' })
      Inscricao.belongsTo(Evento, { foreignKey: 'eventoId' })
      const inscricoes = await Inscricao.findAll({
        where: {
          eventoId: req.params.eventoId
        }, include: [Evento, User]
      })
      res.send(inscricoes)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao buscar a lista de inscrições'
      })
    }
  },
  async geraCertificado(req, res) {
    try {
      console.log('hash:' + hashCertificado(req.params.inscricaoId))
      var inscricao = await Inscricao.findOne({
        where: {
          id: req.params.inscricaoId,
          ind_checkin: 1
        }
      })

      if (!inscricao) {
        return res.status(403).send({
          error: 'Registro de presença não encontrado. Não é permitido gerar o certificado!'
        })
      }

      inscricao = await Inscricao.update({
        des_hash: hashCertificado(req.params.inscricaoId)
      }, {
        where: {
          id: req.params.inscricaoId
        }
      })
      console.log('inscricao:')

      const certificado = {
        idInscricao: req.params.inscricaoId,
        nom_evento: req.body.nom_evento,
        nom_pessoa: req.body.nom_pessoa,
        num_cpf: req.body.num_cpf,
        dta_evento: req.body.dta_evento,
      }
      console.log(GeraCertificado.teste)

      //const r = (await GeraCertificado.gerar(req.params.inscricaoId, certificado)).data

      res.send(certificado)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao gerar certificado' + err
      })
    }
  },
  async post(req, res) {
    try {
      const inscricao = await Inscricao.create(req.body)
      res.send(inscricao)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao salvar inscrição'
      })
    }
  },
  async put(req, res) {
    try {
      const inscricao = await Inscricao.update(req.body, {
        where: {
          id: req.params.inscricaoId
        }
      })
      res.send(inscricao)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao salvar inscrição'
      })
    }
  },
  async delete(req, res) {
    try {
      await Inscricao.destroy({
        where: {
          id: req.params.inscricaoId
        }
      })
      res.send('')
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao deletar inscrição'
      })
    }
  },
  async show(req, res) {
    try {
      const inscricao = await Inscricao.findOne({
        where: {
          id: req.params.inscricaoId
        }
      })
      res.send(inscricao)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao buscar o inscrição'
      })
    }
  }
}
