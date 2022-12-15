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

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUsers users = new QUsers("users");

    public final StringPath adminyn = createString("adminyn");

    public final StringPath adress = createString("adress");

    public final ListPath<kr.worthseeing.main.auction.entity.Auction, kr.worthseeing.main.auction.entity.QAuction> auctionList = this.<kr.worthseeing.main.auction.entity.Auction, kr.worthseeing.main.auction.entity.QAuction>createList("auctionList", kr.worthseeing.main.auction.entity.Auction.class, kr.worthseeing.main.auction.entity.QAuction.class, PathInits.DIRECT2);

    public final StringPath blackyn = createString("blackyn");

    public final ListPath<kr.worthseeing.blockgroup.entity.BlockGroup, kr.worthseeing.blockgroup.entity.QBlockGroup> blockGroupList = this.<kr.worthseeing.blockgroup.entity.BlockGroup, kr.worthseeing.blockgroup.entity.QBlockGroup>createList("blockGroupList", kr.worthseeing.blockgroup.entity.BlockGroup.class, kr.worthseeing.blockgroup.entity.QBlockGroup.class, PathInits.DIRECT2);

    public final StringPath detatiladress = createString("detatiladress");

    public final StringPath email = createString("email");

    public final NumberPath<Integer> finishedAuctionCnt = createNumber("finishedAuctionCnt", Integer.class);

    public final StringPath joindate = createString("joindate");

    public final StringPath name = createString("name");

    public final StringPath nickName = createString("nickName");

    public final ListPath<kr.worthseeing.notify.entity.Notify, kr.worthseeing.notify.entity.QNotify> notifyList = this.<kr.worthseeing.notify.entity.Notify, kr.worthseeing.notify.entity.QNotify>createList("notifyList", kr.worthseeing.notify.entity.Notify.class, kr.worthseeing.notify.entity.QNotify.class, PathInits.DIRECT2);

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final NumberPath<Integer> reservationCnt = createNumber("reservationCnt", Integer.class);

    public final kr.worthseeing.main.reservation.entity.QReservationUserId reservationUserId;

    public final StringPath tel = createString("tel");

    public final NumberPath<Integer> totalMoney = createNumber("totalMoney", Integer.class);

    public final StringPath userId = createString("userId");

    public final StringPath userPw = createString("userPw");

    public QUsers(String variable) {
        this(Users.class, forVariable(variable), INITS);
    }

    public QUsers(Path<? extends Users> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUsers(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUsers(PathMetadata metadata, PathInits inits) {
        this(Users.class, metadata, inits);
    }

    public QUsers(Class<? extends Users> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reservationUserId = inits.isInitialized("reservationUserId") ? new kr.worthseeing.main.reservation.entity.QReservationUserId(forProperty("reservationUserId"), inits.get("reservationUserId")) : null;
    }

}

