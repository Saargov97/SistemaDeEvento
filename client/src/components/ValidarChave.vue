<template>
  <v-layout ml-16 mr-16 mt-8>
    <v-flex>
      <panel title="Validação de certificado">
        <v-text-field label="Chave do certificado:*" v-model="num_chave" required :rules="[required]"></v-text-field>

        <div class="danger-alert" v-if="error">{{error}}</div>
        <v-btn class="green accent-2" @click="save" dark>Validar</v-btn>
      </panel>
    </v-flex>
  </v-layout>
</template>

<script>
import Panel from '@/components/Panel'

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
    async save () {
      this.error = null
      if (!this.num_chave) {
        this.error = 'Informe a chave do certificado!'
        return
      }
      try {

      } catch (err) {
        console.log(err)
      }
    }
  }
}
</script>
<style scoped>
</style>
