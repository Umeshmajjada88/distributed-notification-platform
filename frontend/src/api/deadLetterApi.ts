import api from "./axios";
import { API_CONFIG } from "../config/api";

export async function getDeadLetterStatistics() {

    const url = `${API_CONFIG.delivery}/api/v1/dead-letters/statistics`;

    console.log("Calling:", url);

    const response = await api.get(url);

    return response.data;

}