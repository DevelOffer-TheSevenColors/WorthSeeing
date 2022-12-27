package kr.worthseeing.users.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUsers is a Querydsl query type for Users
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUsers extends EntityPathBase<Users> {

    private static final long serialVersionUID = 1409820795L;

    public static final QUsers users = new QUsers("users");

    public final StringPath address = createString("address");

    public final StringPath adminYn = createString("adminYn");

    public final StringPath blackYn = createString("blackYn");

    public final ListPath<kr.worthseeing.blockgroup.entity.BlockGroup, kr.worthseeing.blockgroup.entity.QBlockGroup> blockGroupList = this.<kr.worthseeing.blockgroup.entity.BlockGroup, kr.worthseeing.blockgroup.entity.QBlockGroup>createList("blockGroupList", kr.worthseeing.blockgroup.entity.BlockGroup.class, kr.worthseeing.blockgroup.entity.QBlockGroup.class, PathInits.DIRECT2);

    public final ListPath<kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting, kr.worthseeing.blockGroupWaiting.entity.QBlockGroupWaiting> blockGroupWaitingList = this.<kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting, kr.worthseeing.blockGroupWaiting.entity.QBlockGroupWaiting>createList("blockGroupWaitingList", kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting.class, kr.worthseeing.blockGroupWaiting.entity.QBlockGroupWaiting.class, PathInits.DIRECT2);

    public final ListPath<kr.worthseeing.event.coupon.entity.Coupon, kr.worthseeing.event.coupon.entity.QCoupon> couponList = this.<kr.worthseeing.event.coupon.entity.Coupon, kr.worthseeing.event.coupon.entity.QCoupon>createList("couponList", kr.worthseeing.event.coupon.entity.Coupon.class, kr.worthseeing.event.coupon.entity.QCoupon.class, PathInits.DIRECT2);

    public final StringPath dailyClickCheck = createString("dailyClickCheck");

    public final StringPath detailAddress = createString("detailAddress");

    public final StringPath email = createString("email");

    public final NumberPath<Integer> finishedAuctionCnt = createNumber("finishedAuctionCnt", Integer.class);

    public final DateTimePath<java.util.Date> joindate = createDateTime("joindate", java.util.Date.class);

    public final StringPath name = createString("name");

    public final StringPath nickName = createString("nickName");

    public final ListPath<kr.worthseeing.notify.entity.Notify, kr.worthseeing.notify.entity.QNotify> notifyList = this.<kr.worthseeing.notify.entity.Notify, kr.worthseeing.notify.entity.QNotify>createList("notifyList", kr.worthseeing.notify.entity.Notify.class, kr.worthseeing.notify.entity.QNotify.class, PathInits.DIRECT2);

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final NumberPath<Integer> reservationCnt = createNumber("reservationCnt", Integer.class);

    public final ListPath<kr.worthseeing.main.reservation.entity.ReservationUsers, kr.worthseeing.main.reservation.entity.QReservationUsers> reservationUsersList = this.<kr.worthseeing.main.reservation.entity.ReservationUsers, kr.worthseeing.main.reservation.entity.QReservationUsers>createList("reservationUsersList", kr.worthseeing.main.reservation.entity.ReservationUsers.class, kr.worthseeing.main.reservation.entity.QReservationUsers.class, PathInits.DIRECT2);

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final StringPath tel = createString("tel");

    public final NumberPath<Integer> totalMoney = createNumber("totalMoney", Integer.class);

    public final StringPath userId = createString("userId");

    public final StringPath userPw = createString("userPw");

    public QUsers(String variable) {
        super(Users.class, forVariable(variable));
    }

    public QUsers(Path<? extends Users> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUsers(PathMetadata metadata) {
        super(Users.class, metadata);
    }

}

