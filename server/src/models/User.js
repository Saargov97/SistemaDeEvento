//const Promise = require('bluebird')
const bcrypt = require('bcrypt')

function hashPassword(user, options) {
  const saltRounds = 10;

  if (!user.changed('password')) {
    return;
  }

  const hash = bcrypt.hashSync(user.password, saltRounds);
  console.log(hash)
  user.setDataValue('password2', user.password)
  user.setDataValue('password', hash)  
  return;
}

module.exports = (sequelize, DataTypes) => {
  const User = sequelize.define('User', {
    email: {
      type: DataTypes.STRING,
      unique: true
    },
    password: DataTypes.STRING,
    password2: DataTypes.STRING,
    nom_pessoa: DataTypes.STRING,
    num_cpf: DataTypes.STRING
  }, {
    hooks: {
      beforeCreate: hashPassword
      // beforeUpdate: hashPassword
      // beforeSave: hashPassword
    }
  })

  User.prototype.comparePassword = function (password) {
    var vtest = bcrypt.compareSync(password, this.password)
    return vtest;
  }

/*  User.associate = models => {
    User.hasMany(models.Evento, {
      onDelete: "cascade"
    }),
    User.hasMany(models.Inscricao, {
      onDelete: "cascade"
    })
  }*/

  return User
}
