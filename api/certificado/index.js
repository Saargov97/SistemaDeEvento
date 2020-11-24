console.log('Waking up fellas')
const express = require("express");
const Promise = require('bluebird');
const bodyParse = require('body-parser')
const cors = require('cors')
const morgan = require('morgan')
const pdf = Promise.promisifyAll(require('html-pdf'));

const app = express();
const port = 3001;

app.use(morgan('combined'))
app.use(bodyParse.json())
app.use(cors())

const geraPdf = async (req) => {
  var conteudo = `
        <div style="width:715px; height:1040px; padding:20px; text-align:center; border: 10px solid #787878">
        <div style="width:665px; height:990px; padding:20px; text-align:center; border: 5px solid #787878">
              <br><br>
              <br><br>
              <span style="font-size:50px; font-weight:bold">Certificado de Participação</span>
              <br><br>
              <br><br>
              <br><br>
              <br><br>
              <span style="font-size:25px"><i>Esse é o Certificado referente ao evento</i></span>
              <br><br>
              <span style="font-size:30px"><b>${req.body.nom_evento}</b></span> <br/><br/>
              <span style="font-size:25px"><i>no qual ${req.body.nom_pessoa}, de CPF nº. ${req.body.num_cpf}, participou no dia</i></span><br/><br/>
              <span style="font-size:30px"><b>${req.body.dta_evento}</b></span> <br/><br/>
              <br/><br/>
              <br/><br/>
              <br/><br/>
              <br/><br/>
              <br/><br/>
              <br/><br/>
              <span style="font-size:18px"><b>Chave de validação:</b> ${req.body.des_hash}
        </div>
        </div>
        `

  let createResult = pdf.create(conteudo, { format: 'A4' });
  let pdfToBuffer = Promise.promisify(createResult.__proto__.toBuffer, { context: createResult });
  let bufferResult = await pdfToBuffer();

  return bufferResult;
}

app.post("/gerarCertificado", async (req, res) => {
  try {
    let bufferResult = await geraPdf(req);

    res.set('Content-Type', 'application/pdf');
    res.send(bufferResult);
  } catch (err) {
    res.status(500).send({
      error: 'Ocorreu um erro ao gerar pdf'
    })
  }
});

app.listen(port, () => console.log(`app listening on port ${port}`));