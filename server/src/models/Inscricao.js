function createQRCode(inscricao, options) {
  inscricao.setDataValue('des_qrcode', `QrEvent_${inscricao.id}_${inscricao.eventoId}_${inscricao.userId}`)
  return;
}

module.exports = (sequelize, DataTypes) => {
  const Inscricao = sequelize.define('Inscricao', {
    eventoId: {
      allowNull: false,
      type: DataTypes.INTEGER,
    },
    userId: {
      allowNull: false,
      type: DataTypes.INTEGER,
    },
    des_hash: {
      type: DataTypes.STRING,
      unique: true
    },
    des_qrcode: {
      type: DataTypes.STRING,
      unique: true
    },
    ind_checkin: {
      allowNull: false,
      type: DataTypes.INTEGER,
      defaultValue: 0
    }
  }, {
    hooks: {
      afterCreate: createQRCode
      // beforeUpdate: hashPassword
      // beforeSave: hashPassword
    }
  })

/*  Inscricao.associate = models => {
    Inscricao.belongsTo(models.User, {
      foreignKey: {
        allowNull: false
      }
    })
    Inscricao.belongsTo(models.Evento, {
      foreignKey: {
        allowNull: false
      }
    })
  }
*/
  return Inscricao
}
