<template>
  <v-layout ml-16 mr-16 mt-8>
    <v-flex>
      <panel title="Bank Metadata">
        <v-text-field label="Name*" v-model="name" required :rules="[required]"></v-text-field>
        <v-text-field label="Code*" v-model="code" required :rules="[required]"></v-text-field>

        <div class="danger-alert" v-if="error">{{error}}</div>
        <v-btn class="cyan" @click="create" dark>Save</v-btn>
        <v-btn class="cyan" @click="navigateTo({name: 'banks'})" dark>Cancel</v-btn>
      </panel>
    </v-flex>
  </v-layout>
</template>

<script>
import Panel from '@/components/Panel'
import BanksService from '@/services/BanksService'
export default {
  data () {
    return {
      name: null,
      code: null,
      error: null,
      required: (value) => !!value || 'Required.'
    }
  },
  methods: {
    async create () {
      this.error = null
      const bank = {
        name: this.name,
        code: this.code
      }
      const areAllFieldsFilledIn = Object
        .keys(bank)
        .every(key => !!bank[key])
      if (!areAllFieldsFilledIn) {
        this.error = 'Informe todos os campos obrigatórios'
        return
      }
      try {
        await BanksService.post(bank)
        this.$router.push({ name: 'banks' })
      } catch (err) {
        console.log(err)
      }
    },
    navigateTo (route) {
      this.$router.push(route)
    }
  },
  components: {
    Panel
  }
}
</script>
<style scoped>
</style>
