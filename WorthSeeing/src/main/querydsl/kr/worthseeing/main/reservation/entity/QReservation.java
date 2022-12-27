package kr.worthseeing.main.reservation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = -1105986784L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservation reservation = new QReservation("reservation");

    public final ListPath<kr.worthseeing.main.auction.entity.Auction, kr.worthseeing.main.auction.entity.QAuction> auctionList = this.<kr.worthseeing.main.auction.entity.Auction, kr.worthseeing.main.auction.entity.QAuction>createList("auctionList", kr.worthseeing.main.auction.entity.Auction.class, kr.worthseeing.main.auction.entity.QAuction.class, PathInits.DIRECT2);

    public final kr.worthseeing.blockGroupWaiting.entity.QBlockGroupWaiting blockGroupWaiting;

    public final NumberPath<Integer> reservation_seq = createNumber("reservation_seq", Integer.class);

    public final ListPath<ReservationUsers, QReservationUsers> reservationUsersList = this.<ReservationUsers, QReservationUsers>createList("reservationUsersList", ReservationUsers.class, QReservationUsers.class, PathInits.DIRECT2);

    public final NumberPath<Integer> startPrice = createNumber("startPrice", Integer.class);

    public final NumberPath<Integer> userCnt = createNumber("userCnt", Integer.class);

    public QReservation(String variable) {
        this(Reservation.class, forVariable(variable), INITS);
    }

    public QReservation(Path<? extends Reservation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservation(PathMetadata metadata, PathInits inits) {
        this(Reservation.class, metadata, inits);
    }

    public QReservation(Class<? extends Reservation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.blockGroupWaiting = inits.isInitialized("blockGroupWaiting") ? new kr.worthseeing.blockGroupWaiting.entity.QBlockGroupWaiting(forProperty("blockGroupWaiting"), inits.get("blockGroupWaiting")) : null;
    }

}

