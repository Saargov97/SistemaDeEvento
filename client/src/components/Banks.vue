<template>
  <v-layout ml-16 mr-16 mt-8>
    <v-flex>
      <panel title="Banks">
          <v-btn slot="newButton" class="cyan accent-2" fab ligth small absolute right middle @click="navigateTo({name: 'banks-create'})">
            <v-icon>add</v-icon>
          </v-btn>
          <v-row>
            <v-col cols="12" sm="6" md="4">
              Name
            </v-col>
            <v-col cols="12" sm="6" md="4">
              Code
            </v-col>
            <v-col cols="6" md="4">
              Actions
            </v-col>
          </v-row>
          <div v-for="bank in banks" :key="bank.id">
              <v-row>
                <v-col cols="12" sm="6" md="4">
                    {{bank.name}}
                </v-col>
                <v-col cols="12" sm="6" md="4">
                    {{bank.code}}
                </v-col>
                <v-col cols="6" sm="1" md="4" >
                  <v-btn class="green accent-2" fab ligth small right middle @click="navigateTo({name: 'bank', params: {bankId: bank.id}})">
                    <v-icon>edit</v-icon>
                  </v-btn>
                  <v-btn class="red accent-1" fab ligth small right middle @click="deletebank({bankId: bank.id})">
                    <v-icon>delete</v-icon>
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
import BanksService from '@/services/BanksService'
export default {
  components: {
    Panel
  },
  data () {
    return {
      banks: null
    }
  },
  async mounted () {
    // do a request to the backend for all the banks
    this.banks = (await BanksService.index()).data
  },
  methods: {
    navigateTo (route) {
      this.$router.push(route)
    },
    async deletebank (bankId) {
      try {
        confirm('Are you sure you want to delete this item?') && await BanksService.delete(bankId.bankId)
        this.banks = (await BanksService.index()).data
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
