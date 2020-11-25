const { Inscricao } = require('../models')
const { User } = require('../models')
const { Evento } = require('../models')
const md5 = require('md5');
const fetch = require('node-fetch');


function hashCertificado(inscricaoId, options) {
  //const saltRounds = 10;

  //const hash = bcrypt.hashSync(`${inscricaoId}`, saltRounds);
  //console.log(hash)
  return md5(`${inscricaoId}`);
}

async function EnviarEmail(destinatario, assunto, mensagem){
  const req = {
    destinatario: destinatario,
    assunto: assunto,
    texto: mensagem
  }
  await fetch('http://localhost:3002/enviarEmail', {
        method: 'POST',
        body: JSON.stringify(req),
        headers: { 'Content-Type': 'application/json' },
      });
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
      console.log(req.params.inscricaoId)
      const des_hash = hashCertificado(req.params.inscricaoId);
      User.hasMany(Inscricao, { foreignKey: 'userId' })
      Inscricao.belongsTo(User, { foreignKey: 'userId' })
      Evento.hasMany(Inscricao, { foreignKey: 'eventoId' })
      Inscricao.belongsTo(Evento, { foreignKey: 'eventoId' })
      var inscricao = await Inscricao.findOne({
        where: {
          id: req.params.inscricaoId,
          ind_checkin: 1
        }, include: [Evento, User]
      })

      if (!inscricao) {
         return res.status(403).send({
           error: 'Registro de presença não encontrado. Não é permitido gerar o certificado!'
         })
       }

      EnviarEmail(inscricao.User.dataValues.email, 'Certificado gerado', `Seu certificado para o evento ${inscricao.Evento.dataValues.nom_evento} foi gerado!`)

      const certificado = {
        idInscricao: req.params.inscricaoId,
        nom_evento: inscricao.Evento.dataValues.nom_evento,
        nom_pessoa: inscricao.User.dataValues.nom_pessoa,
        num_cpf: inscricao.User.dataValues.num_cpf,
        dta_evento: inscricao.Evento.dataValues.dta_evento,
        des_hash: des_hash
      }

      inscricao = await Inscricao.update({
        des_hash: des_hash
      }, {
        where: {
          id: req.params.inscricaoId
        }
      })     

      const response = await fetch('http://localhost:3001/gerarCertificado', {
        method: 'POST',
        body: JSON.stringify(certificado),
        headers: { 'Content-Type': 'application/json' },
      });
      const buffer = await response.buffer();


      //console.log(buffer)

      res.setHeader('Content-Type', 'application/pdf');
      res.send(buffer);
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao gerar certificado' + err
      })
    }
  },
  async validaCertificado(req, res) {
    try {
      User.hasMany(Inscricao, { foreignKey: 'userId' })
      Inscricao.belongsTo(User, { foreignKey: 'userId' })
      Evento.hasMany(Inscricao, { foreignKey: 'eventoId' })
      Inscricao.belongsTo(Evento, { foreignKey: 'eventoId' })
      var inscricao = await Inscricao.findOne({
        where: {
          des_hash: req.params.desHash
        }, include: [Evento, User]
      })

      if (!inscricao) {
        return res.status(403).send({
          error: 'Chave do certificado não encontrada!'
        })
      }

      res.send(inscricao)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao validar chave do certificado' + err
      })
    }
  },
  async post(req, res) {
    try {
      const user = await User.findOne({
        where: {
          id: req.body.userId
        }
      })
      const evento = await Evento.findOne({
        where: {
          id: req.body.eventoId
        }
      })
      const inscricao = await Inscricao.create(req.body)
      EnviarEmail(user.email, 'Inscrição evento', `Você foi inscrito no evento ${evento.nom_evento}!`)
      res.send(inscricao)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao salvar inscrição'
      })
    }
  },
  async checkin(req, res) {
    try {
      var inscricao = await Inscricao.update({
        ind_checkin: 1
      }, {
        where: {
          id: req.params.inscricaoId
        }
      })
      inscricao = await Inscricao.findOne({
        where: {
          id: req.params.inscricaoId
        }
      })
      console.log(inscricao)
      const user = await User.findOne({
        where: {
          id: inscricao.userId
        }
      })
      const evento = await Evento.findOne({
        where: {
          id: inscricao.eventoId
        }
      })
      EnviarEmail(user.email, 'Presença evento', `Sua presença no evento ${evento.nom_evento} foi confirmada!`)
      res.send(inscricao)
    } catch (err) {
      res.status(500).send({
        error: 'Ocorreu um erro ao registrar o checkin'
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
      const inscricao = await Inscricao.findOne({
        where: {
          id: req.params.inscricaoId
        }
      })
      const user = await User.findOne({
        where: {
          id: inscricao.userId
        }
      })
      const evento = await Evento.findOne({
        where: {
          id: inscricao.eventoId
        }
      })
      EnviarEmail(user.email, 'Cancelamento de inscrição', `Foi cancelado sua inscrição no evento ${evento.nom_evento}!`)
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
