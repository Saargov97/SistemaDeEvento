<template>
  <v-layout ml-16 mr-16 mt-8>
    <v-flex>
      <panel title="Banks">
          <v-col slot="buttons" class="text-right">
            <v-btn class="cyan accent-2" fab ligth small right middle @click="navigateTo({name: 'banks-create'})">
              <v-icon>add</v-icon>
            </v-btn>
            <v-btn class="cyan accent-2" fab ligth small right middle @click="navigateTo({name: 'banks-create'})">
              <v-icon>edit</v-icon>
            </v-btn>
            <v-btn class="cyan accent-2" fab ligth small right middle @click="navigateTo({name: 'banks-create'})">
              <v-icon>delete</v-icon>
            </v-btn>
          </v-col>

          <v-data-table
            v-model="selected"
            :headers="headers"
            :items="banks"
            :single-select="singleSelect"
            item-key="id"
            show-select
            class="elevation-1"
            loading-text="Loading... Please wait"
            v-if="banks">{{loading}}
          >

          <template v-slot:top>
                <v-toolbar flat color="white">
                  <v-toolbar-title>Banks</v-toolbar-title>
                  <v-divider
                    class="mx-4"
                    inset
                    vertical
                  ></v-divider>
                  <v-spacer></v-spacer>
                  <v-dialog v-model="dialog" max-width="500px">
                    <template v-slot:activator="{ on, attrs }">
                      <v-btn
                        color="primary"
                        dark
                        class="mb-2"
                        v-bind="attrs"
                        v-on="on"
                      >New Item</v-btn>
                    </template>
                    <v-card>
                      <v-card-title>
                        <span class="headline">{{ formTitle }}</span>
                      </v-card-title>

                      <v-card-text>
                        <v-container>
                          <v-row>
                            <v-col>
                              <v-text-field v-model="editedItem.name" label="Name:"></v-text-field>
                            </v-col>
                          </v-row>
                          <v-row>
                            <v-col>
                              <v-text-field v-model="editedItem.code" label="Code:"></v-text-field>
                            </v-col>
                          </v-row>
                        </v-container>
                      </v-card-text>

                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
                        <v-btn color="blue darken-1" text @click="save">Save</v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </v-toolbar>
              </template>
              <template v-slot:item.actions="{ item }">
                <v-icon
                  small
                  class="mr-2"
                  @click="editItem(item)"
                >
                  mdi-pencil
                </v-icon>
                <v-icon
                  small
                  @click="deleteItem(item)"
                >
                  mdi-delete
                </v-icon>
              </template>
              <template v-slot:no-data>
                <v-btn color="primary" @click="initialize">Reset</v-btn>
              </template>
          </v-data-table>
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
      singleSelect: true,
      selected: [],
      headers: [
        { text: 'Banks', align: 'start', sortable: false, value: 'id' },
        { text: 'Name', value: 'name' },
        { text: 'Code', value: 'code' }
      ],
      banks: [{}],
      editedIndex: -1,
      editedItem: {
        id: 0,
        name: '',
        code: ''
      },
      defaultItem: {
        id: 0,
        name: '',
        code: ''
      }
    }
  },
  async mounted () {
    // do a request to the backend for all the banks
    this.banks = (await BanksService.index()).data
  },
  computed: {
    formTitle () {
      return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
    }
  },
  watch: {
    dialog (val) {
      val || this.close()
    }
  },
  methods: {
    navigateTo (route) {
      this.$router.push(route)
    },
    async deletebank (bankId) {
      try {
        await BanksService.delete(bankId.bankId)
        this.$router.go(0)
        // this.$router.push({ name: 'banks' })
      } catch (error) {
        this.error = error.response.data.error
      }
    },
    editItem (item) {
      this.editedIndex = this.banks.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },
    deleteItem (item) {
      const index = this.banks.indexOf(item)
      confirm('Are you sure you want to delete this item?') && this.banks.splice(index, 1)
    },
    close () {
      this.dialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },
    save () {
      if (this.editedIndex > -1) {
        Object.assign(this.banks[this.editedIndex], this.editedItem)
      } else {
        this.banks.push(this.editedItem)
      }
      this.close()
    }
  }
}
</script>
<style scoped>
</style>
