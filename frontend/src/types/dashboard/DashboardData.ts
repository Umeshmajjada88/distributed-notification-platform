import type { NotificationStatistics } from "./NotificationStatistics";
import type { DeliveryStatistics } from "./DeliveryStatistics";
import type { DeadLetterStatistics } from "./DeadLetterStatistics";

export interface DashboardData {

    notifications: NotificationStatistics;

    deliveries: DeliveryStatistics;

    deadLetters: DeadLetterStatistics;

}