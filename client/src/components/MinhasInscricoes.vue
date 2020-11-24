<template>
  <v-layout ml-16 mr-16 mt-8>
    <v-flex>
      <panel title="Minhas inscrições">
          <v-row>
            <v-col cols="12" sm="4" md="3">
              Nome
            </v-col>
            <v-col cols="6" sm="2">
              Data
            </v-col>
            <v-col cols="6" sm="2">
              Valor
            </v-col>
            <v-col cols="6" sm="2">
              Carga Horária
            </v-col>
            <v-col cols="6" md="2">
              Ações
            </v-col>
          </v-row>
          <div v-for="inscricao in inscricoes" :key="inscricao.id">
              <v-row>
                <v-col cols="12" sm="4" md="3">
                    {{inscricao.Evento.nom_evento}}
                </v-col>
                <v-col cols="6" sm="2">
                    {{inscricao.Evento.dta_evento | formatDate}}
                </v-col>
                <v-col cols="6" sm="2">
                    {{inscricao.Evento.vlr_evento | toCurrency}}
                </v-col>
                <v-col cols="6" sm="2">
                    {{inscricao.Evento.des_carga_horaria}}
                </v-col>
                <v-col cols="6" md="2">
                   <v-btn class="green accent-2" fab ligth small right middle @click="downloadCertificado({inscricaoId: inscricao.id})">
                    <v-icon>save_alt</v-icon>
                  </v-btn>
                  <v-btn class="blue accent-2" fab ligth small right middle @click="openCertificado({inscricaoId: inscricao.id})">
                    <v-icon>restore_page</v-icon>
                  </v-btn>
                  <v-btn class="red accent-2" fab ligth small right middle @click="desinscrever({inscricaoId: inscricao.id})">
                    <v-icon>cancel</v-icon>
                  </v-btn>
                </v-col>
              </v-row>
          </div>
      </panel>
      </v-flex>
  </v-layout>
</template>

<script>
import Panel from '@/components/Panel'
import InscricoesService from '@/services/InscricoesService'
import axios from 'axios'

export default {
  components: {
    Panel
  },
  data () {
    return {
      inscricoes: null,
      eventos: null,
      erros: null,
      response: null
    }
  },
  async mounted () {
    this.inscricoes = (await InscricoesService.user(this.$store.state.user.id)).data
  },
  methods: {
    navigateTo (route) {
      this.$router.push(route)
    },
    async desinscrever (inscricaoId) {
      try {
        confirm('Confirma a desinscrição do evento?') && await InscricoesService.delete(inscricaoId.inscricaoId)
        this.inscricoes = (await InscricoesService.user(this.$store.state.user.id)).data
        // this.$router.push({ name: 'banks' })
      } catch (error) {
        this.error = error.response.data.error
      }
    },
    async openCertificado (inscricaoId) {
      /*
      const response = await InscricoesService.geraCertificado(inscricaoId.inscricaoId)
      const file = new Blob([response.data])
      const fileURL = URL.createObjectURL(file)
      window.open(fileURL)
      */
      axios({
        url: `http://localhost:8081/geraCertificado/${inscricaoId.inscricaoId}`,
        method: 'POST',
        responseType: 'blob'
      }).then((response) => {
        var fileURL = window.URL.createObjectURL(new Blob([response.data]))
        window.open(fileURL)
      })
    },
    async downloadCertificado (inscricaoId) {
      axios({
        url: `http://localhost:8081/geraCertificado/${inscricaoId.inscricaoId}`,
        method: 'POST',
        responseType: 'blob'
      }).then((response) => {
        var fileURL = window.URL.createObjectURL(new Blob([response.data]))
        var fileLink = document.createElement('a')
        fileLink.href = fileURL
        fileLink.setAttribute('download', 'certification.pdf')
        document.body.appendChild(fileLink)

        fileLink.click()
      }).catch((error) => {
        this.response = error.response
        alert(this.response)
      })
    }
  }
}
</script>
<style scoped>
</style>
