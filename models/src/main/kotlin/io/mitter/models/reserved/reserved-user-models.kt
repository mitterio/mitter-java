import io.mitter.data.domain.user.ScreenName
import io.mitter.data.domain.user.User
import io.mitter.named.reserved.ReservedSystemUserNames

object ReservedUserModels {
    val DeletedUser = User()
        .apply {
            userId = ReservedSystemUserNames.DeletedUserName
            screenName = ScreenName().apply { screenName = ReservedSystemUserNames.DeletedUserName }
            isSystemUser = true
            isBlocked = true
            isSynthetic = true
        }
}
