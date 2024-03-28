<template>
  <!--      <div class="product-title"> -->

  <!--      </div> -->
  <div class="skeleton" />
</template>

<style>
.skeleton {
  width: 100%;
  height: 200px;
  background-color: #e3e3e3;
  border-radius: 2.14vw;
  position: relative;
  overflow: hidden;
}

@keyframes pulse {
  from {
    left: 100%;
  }
  to {
    right: 0;
  }
}
.skeleton::after {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: linear-gradient(
    90deg,
    transparent,
    rgba(255, 255, 255, 0.8),
    transparent
  ); /* Эффект мигания */
  animation: pulse 1.5s infinite alternate;
}
</style>
