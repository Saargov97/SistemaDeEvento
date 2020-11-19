//const Promise = require('bluebird')
const bcrypt = require('bcrypt')

function hashPassword(user, options) {
  const saltRounds = 10;

  if (!user.changed('password')) {
    return;
  }

  const hash = bcrypt.hashSync(user.password, saltRounds);
  console.log(hash)
  user.setDataValue('password', hash)
  return;

  return bcrypt
    .genSaltAsync(SALT_FACTOR)
    .then(salt => bcrypt.hashAsync(user.password, salt, null))
    .then(hash => {
      user.setDataValue('password', hash)
    })
}

module.exports = (sequelize, DataTypes) => {
  const User = sequelize.define('User', {
    email: {
      type: DataTypes.STRING,
      unique: true
    },
    password: DataTypes.STRING
  }, {
    hooks: {
      beforeCreate: hashPassword
      //  beforeUpdate: hashPassword
      // beforeSave: hashPassword
    }
  })

  User.prototype.comparePassword = function (password) {
    var vtest = bcrypt.compareSync(password, this.password)
    return vtest;
  }

  return User
}
