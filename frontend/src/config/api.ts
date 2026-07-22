export const API_CONFIG = {

    notification: import.meta.env.VITE_NOTIFICATION_API,

    delivery: import.meta.env.VITE_DELIVERY_API

};

console.log("Notification API =", API_CONFIG.notification);
console.log("Delivery API =", API_CONFIG.delivery);
console.log("All env =", import.meta.env);