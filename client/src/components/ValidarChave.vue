<template>
  <v-layout ml-16 mr-16 mt-8>
    <v-flex>
      <panel title="Validação de certificado">
        <v-text-field label="Chave do certificado:*" v-model="num_chave" required :rules="[required]"></v-text-field>

        <div class="danger-alert" v-if="error">{{error}}</div>
        <v-btn class="green accent-2" @click="validarChave" dark>Validar</v-btn>
      </panel>
    </v-flex>
  </v-layout>
</template>

<script>
import Panel from '@/components/Panel'
import InscricoesService from '@/services/InscricoesService'

export default {
  data () {
    return {
      num_chave: null,
      error: null,
      required: (value) => !!value || 'Required.'
    }
  },
  async mounted () {
  },
  components: {
    Panel
  },
  methods: {
    navigateTo (route) {
      this.$router.push(route)
    },
    async validarChave () {
      this.error = null
      if (!this.num_chave) {
        this.error = 'Informe a chave do certificado!'
        return
      }
      try {
        const res = (await InscricoesService.validarChave(this.num_chave)).data
        alert(`Chave válida! Evento: ${res.Evento.nom_evento} Participante: ${res.User.nom_pessoa}`)
      } catch (err) {
        this.error = err.response.data.error
      }
    }
  }
}
</script>
<style scoped>
</style>
