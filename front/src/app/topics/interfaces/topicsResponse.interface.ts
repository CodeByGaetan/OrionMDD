import { Topic } from "./topic.interface";

export interface TopicsResponse {
    subTopicIds: number[],
    topics: Topic[]
}