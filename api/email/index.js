console.log('Waking up fellas')
const express = require("express");
const nodemailer = require('nodemailer');
const bodyParse = require('body-parser')
const cors = require('cors')
const morgan = require('morgan')

const app = express();
const port = 3002;
app.use(morgan('combined'))
app.use(bodyParse.json())
app.use(cors())

const user = "fincontrol.project@gmail.com";
const pass = "fincontrol@2020";

app.post('/enviarEmail', async (req, res) => {
  try {
    console.log(req.body)
    const transporter = nodemailer.createTransport({
      service: 'gmail',
      auth: { user, pass }
    })

    const mailOptions = {
      from: user,
      to: req.body.destinatario,
      subject: req.body.assunto,
      text: req.body.texto
    }

    transporter.sendMail(mailOptions, function (error, info) {
      if (error) {
        console.log('Erro ao enviar e-mail: ' + error);
      } else {
        console.log('E-mail enviado com sucesso!');
      }
    })
    res.status(200).send('Enviado')
  } catch (error) {
    res.status(500).send({
      error: 'Ocorreu um erro ao enviar e-mail' + err
    })
  }
})

app.listen(port, () => console.log(`app listening on port ${port}`));