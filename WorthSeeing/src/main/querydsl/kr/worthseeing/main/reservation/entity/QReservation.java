package kr.worthseeing.main.reservation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = -1105986784L;

    public static final QReservation reservation = new QReservation("reservation");

    public final NumberPath<Integer> groupblock_seq = createNumber("groupblock_seq", Integer.class);

    public final NumberPath<Integer> reservation_seq = createNumber("reservation_seq", Integer.class);

    public final NumberPath<Integer> startPrice = createNumber("startPrice", Integer.class);

    public final NumberPath<Integer> userCnt = createNumber("userCnt", Integer.class);

    public QReservation(String variable) {
        super(Reservation.class, forVariable(variable));
    }

    public QReservation(Path<? extends Reservation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservation(PathMetadata metadata) {
        super(Reservation.class, metadata);
    }

}

