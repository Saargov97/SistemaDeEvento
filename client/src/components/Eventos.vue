<template>
  <v-layout ml-16 mr-16 mt-8>
    <v-flex>
      <panel title="Eventos">
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
              Increver-se
            </v-col>
          </v-row>
          <div v-for="evento in eventos" :key="evento.id" :rows="formatedItems">
              <v-row>
                <v-col cols="12" sm="4" md="3">
                    {{evento.nom_evento}}
                </v-col>
                <v-col cols="6" sm="2">
                    {{evento.dta_evento | formatDate}}
                </v-col>
                <v-col cols="6" sm="2">
                    {{evento.vlr_evento | toCurrency}}
                </v-col>
                <v-col cols="6" sm="2">
                    {{evento.des_carga_horaria}}
                </v-col>
                <v-col cols="6" md="2">
                  <v-btn class="green accent-2" fab ligth small right middle @click="deletebank({eventoId: evento.id})">
                    <v-icon>add_box</v-icon>
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
import EventosService from '@/services/EventosService'
export default {
  components: {
    Panel
  },
  data () {
    return {
      eventos: null
    }
  },
  async mounted () {
    // do a request to the backend for all the banks
    this.eventos = (await EventosService.index()).data
  },
  methods: {
    navigateTo (route) {
      this.$router.push(route)
    },
    async deletebank (eventoId) {
      try {
        confirm('Are you sure you want to delete this item?') && await EventosService.delete(eventoId.eventoId)
        this.eventos = (await EventosService.index()).data
        // this.$router.push({ name: 'banks' })
      } catch (error) {
        this.error = error.response.data.error
      }
    }
  }
}
</script>
<style scoped>
</style>
