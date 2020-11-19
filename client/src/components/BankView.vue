<template>
  <v-layout ml-16 mr-16 mt-8>
    <v-flex>
      <panel title="Bank Metadata">
        <v-text-field label="Name*" v-model="name" required :rules="[required]"></v-text-field>
        <v-text-field label="Code*" v-model="code" required :rules="[required]"></v-text-field>

        <div class="danger-alert" v-if="error">{{error}}</div>
        <v-btn class="cyan" @click="save" dark>Save</v-btn>
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
      bank: {},
      name: null,
      code: null,
      error: null,
      required: (value) => !!value || 'Required.'
    }
  },
  async mounted () {
    const bankId = this.$store.state.route.params.bankId
    this.bank = (await BanksService.show(bankId)).data
    this.name = this.bank.name
    this.code = this.bank.code
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
      const bank = {
        id: this.$store.state.route.params.bankId,
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
        await BanksService.put(bank)
        this.$router.push({ name: 'banks' })
      } catch (err) {
        console.log(err)
      }
    }
  }
}
</script>
<style scoped>
</style>
