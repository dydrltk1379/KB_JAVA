<template>
  <div class="container-fluid py-4">
    <div class="row">
      <div class="col-12">
        <div class="multisteps-form mb-9">
          <div class="row">
            <div class="col-12 col-lg-8 mx-auto my-5"></div>
          </div>
          <div class="row">
            <div class="col-12 col-lg-8 m-auto">
              <div class="card">
                <div
                  class="card-header p-0 position-relative mt-n4 mx-3 z-index-2"
                >
                  <div
                    class="bg-gradient-success shadow-success border-radius-lg pt-4 pb-3"
                  >
                    <div class="multisteps-form__progress">
                      <button
                        class="multisteps-form__progress-btn"
                        type="button"
                        title="User Info"
                        :class="activeStep >= 0 ? activeClass : ''"
                        @click="activeStep = 0"
                      >
                        <span>User Info</span>
                      </button>
                      <button
                        class="multisteps-form__progress-btn"
                        type="button"
                        title="Address"
                        :class="activeStep >= 1 ? activeClass : ''"
                        @click="activeStep = 1"
                      >
                        Address
                      </button>
                      <button
                        class="multisteps-form__progress-btn"
                        type="button"
                        title="Socials"
                        :class="activeStep >= 2 ? activeClass : ''"
                        @click="activeStep = 2"
                      >
                        Socials
                      </button>
                      <button
                        class="multisteps-form__progress-btn"
                        type="button"
                        title="Profile"
                        :class="activeStep >= 3 ? activeClass : ''"
                        @click="activeStep = 3"
                      >
                        Profile
                      </button>
                    </div>
                  </div>
                </div>
                <div class="card-body">
                  <form class="multisteps-form__form">
                    <!--single form panel-->
                    <user-info :class="activeStep === 0 ? activeClass : ''" />
                    <!--single form panel-->
                    <the-address :class="activeStep === 1 ? activeClass : ''" />
                    <!--single form panel-->
                    <socials :class="activeStep === 2 ? activeClass : ''" />
                    <!--single form panel-->
                    <profile :class="activeStep === 3 ? activeClass : ''" />
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import UserInfo from "./components/UserInfo.vue";
import TheAddress from "./components/Address.vue";
import Socials from "./components/Socials.vue";
import Profile from "./components/Profile.vue";

export default {
  name: "NewUser",
  components: {
    UserInfo,
    TheAddress,
    Socials,
    Profile,
  },
  data() {
    return {
      showMenu: false,
      activeClass: "js-active position-relative",
      activeStep: 0,
      formSteps: 3,
    };
  },
  methods: {
    nextStep() {
      if (this.activeStep < this.formSteps) {
        this.activeStep += 1;
        console.log(this.activeStep);
      } else {
        this.activeStep -= 1;
        console.log(this.activeStep);
      }
    },
    prevStep() {
      if (this.activeStep > 0) {
        this.activeStep -= 1;
        console.log(this.activeStep);
      }
    },
  },
};
</script>
