module.exports = (sequelize, DataTypes) => {
  const Bank = sequelize.define('Bank', {
    name: {
        type: DataTypes.STRING,
        unique: true
    },
    code: DataTypes.STRING
  })

  return Bank
}
