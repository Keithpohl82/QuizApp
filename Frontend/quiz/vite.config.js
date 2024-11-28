import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      global: 'rollup-plugin-node-globals',
      process: 'rollup-plugin-node-process',
    },
  },
  define: {
    global: 'window',
  },
});
