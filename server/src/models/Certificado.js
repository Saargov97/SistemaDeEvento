module.exports = (sequelize, DataTypes) => {
  const Certificado = sequelize.define('Certificado', {
    nom_certificado: {
        type: DataTypes.STRING,
        unique: true
    },
    dta_geracao: {
      allowNull: false,
      type: DataTypes.DATE
    },
    des_hash: {
      type: DataTypes.STRING,
      unique: true
    }
  })

  return Certificado
}
