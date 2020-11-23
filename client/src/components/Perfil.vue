<template>
  <v-layout ml-16 mr-16 mt-8>
    <v-flex>
      <panel title="Perfil">
        <v-text-field label="Nome:*" v-model="nom_pessoa" required :rules="[required]"></v-text-field>
        <v-text-field label="CPF:*" v-model="num_cpf" required :rules="[required]"></v-text-field>

        <div class="danger-alert" v-if="error">{{error}}</div>
        <v-btn class="green accent-2" @click="save" dark>Salvar</v-btn>
        <v-btn class="red accent-2" @click="navigateTo({name: 'root'})" dark>Cancelar</v-btn>
      </panel>
    </v-flex>
  </v-layout>
</template>

<script>
import Panel from '@/components/Panel'
import AuthenticationService from '@/services/AuthenticationService'
export default {
  data () {
    return {
      user: {},
      nom_pessoa: null,
      num_cpf: null,
      error: null,
      required: (value) => !!value || 'Required.'
    }
  },
  async mounted () {
    const userId = this.$store.state.user.id
    this.user = (await AuthenticationService.show(userId)).data
    this.nom_pessoa = this.user.nom_pessoa
    this.num_cpf = this.user.num_cpf
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
      const user = {
        id: this.$store.state.user.id,
        nom_pessoa: this.nom_pessoa,
        num_cpf: this.num_cpf
      }
      const areAllFieldsFilledIn = Object
        .keys(user)
        .every(key => !!user[key])
      if (!areAllFieldsFilledIn) {
        this.error = 'Informe todos os campos obrigatórios'
        return
      }
      try {
        await AuthenticationService.put(user)
        const response = await AuthenticationService.show(this.$store.state.user.id)
        this.$store.dispatch('setUser', response.data)
        this.$router.push({ name: 'root' })
      } catch (err) {
        console.log(err)
      }
    }
  }
}
</script>
<style scoped>
</style>
