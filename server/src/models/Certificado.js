const bcrypt = require('bcrypt')

function hashCertificado(certificado, options) {
  const saltRounds = 10;

  const hash = bcrypt.hashSync(`${certificado.id}` || `1`, saltRounds);
  console.log(hash)
  certificado.setDataValue('des_hash', hash)
  return;
}

module.exports = (sequelize, DataTypes) => {
  const Certificado = sequelize.define('Certificado', {
    des_hash: {
      type: DataTypes.STRING,
      unique: true
    }
  }, {
    hooks: {
      beforeCreate: hashCertificado
      // beforeUpdate: hashPassword
      // beforeSave: hashCertificado
    }
  })

  return Certificado
}
