package univ.tuit.krill_lotin.ext;


import univ.tuit.krill_lotin.utils.JsonUtil;

public interface JsonSerializable {
    default String toJson() {
        return JsonUtil.toJson(this);
    }

    default String toPrettyJson() {
        return JsonUtil.toPrettyJson(this);
    }
}
