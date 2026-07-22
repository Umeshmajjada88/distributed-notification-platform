import { getNotificationStatistics } from "../api/notificationApi";
import { getDeliveryStatistics } from "../api/deliveryApi";
import { getDeadLetterStatistics } from "../api/deadLetterApi";
import type { DashboardData } from "../types/dashboard/DashboardData";

export async function getDashboardData(): Promise<DashboardData> {

    const [

        notifications,

        deliveries,

        deadLetters

    ] = await Promise.all([

        getNotificationStatistics(),

        getDeliveryStatistics(),

        getDeadLetterStatistics()

    ]);

    console.log("Notifications:", notifications);
    console.log("Deliveries:", deliveries);
    console.log("DeadLetters:", deadLetters);

    return {

        notifications,

        deliveries,

        deadLetters

    };

}