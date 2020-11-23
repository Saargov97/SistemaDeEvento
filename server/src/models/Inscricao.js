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
    des_qrcode: {
      type: DataTypes.STRING,
      unique: true
    },
    certificadoId: {
      type: DataTypes.INTEGER,
    },
    ind_checkin: {
      allowNull: false,
      type: DataTypes.INTEGER,
      defaultValue: 0
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
