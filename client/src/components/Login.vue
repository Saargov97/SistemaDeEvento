<template>
  <v-layout ml-16 mr-16 mt-8>
    <v-flex>
      <panel title="Login">
        <br />
        <v-text-field required
          label="E-mail"
          v-model="email">
        </v-text-field>
        <br />
        <v-text-field required
          label="Password"
          type="password"
          v-model="password">
        </v-text-field>
        <br />
        <div class="error" v-html="error" />
        <br />
        <v-btn class="cyan" @click="login" dark>Login</v-btn>
      </panel>
    </v-flex>
  </v-layout>
</template>

<script>
import AuthenticationService from '@/services/AuthenticationService'
import Panel from '@/components/Panel'
export default {
  data () {
    return {
      email: '',
      password: '',
      error: null
    }
  },
  watch: {
    email (value) {
      console.log('email has changed: ', value)
    }
  },
  methods: {
    async login () {
      try {
        const response = await AuthenticationService.login({
          email: this.email,
          password: this.password
        })
        this.$store.dispatch('setToken', response.data.token)
        this.$store.dispatch('setUser', response.data.user)
        if (response.data.token) {
          this.$store.dispatch('setIsUserLoggedIn', true)
        } else {
          this.$store.dispatch('setIsUserLoggedIn', false)
        }
        this.$router.push('root')
      } catch (error) {
        this.error = error.response.data.error
      }
    }
  },
  components: {
    Panel
  }
  // mounted () {
  //   setTimeout(() => {
  //     this.email = 'hello world'
  //   }, 2000)
  // }
}
</script>

<style scoped>
.error {
  color: red
}
</style>
