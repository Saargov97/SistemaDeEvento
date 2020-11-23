module.exports = (sequelize, DataTypes) => {
  const Evento = sequelize.define('Evento', {
    nom_evento: {
        type: DataTypes.STRING,
        unique: true
    },
    dta_evento: {
      allowNull: false,
      type: DataTypes.DATE
    },
    vlr_evento: {
      type: DataTypes.DECIMAL
    },
    des_carga_horaria: {
      type: DataTypes.STRING,
    },
    num_vaga: {
      type: DataTypes.INTEGER,
    },
    userId: {
      allowNull: false,
      type: DataTypes.INTEGER,
    }
  })

  return Evento
}
