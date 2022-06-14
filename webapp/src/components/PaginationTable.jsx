import React from "react";

import { Pagination, PaginationItem, PaginationLink } from 'reactstrap';

function PaginationTable(props) {


    /* 
    Pageable java

    totalElements={data[0].totalElements}
    totalPages={data[0].totalPages}
    size={data[0].size}
    number={data[0].number}
    first={data[0].first}
    last={data[0].last}
    empty={data[0].empty}
    */
    

    return (
        <>
        <div style={{ display: "flex", justifyContent: "center" }}>
            <Pagination 
                aria-label="Page navigation example"
                size="sm"
            >
                <PaginationItem>
                    <PaginationLink
                        first
                        href="#"
                    />
                </PaginationItem>
                <PaginationItem>
                    <PaginationLink
                        href="#"
                        previous
                    />
                </PaginationItem>
                <PaginationItem>
                    <PaginationLink href="#">
                        1
                    </PaginationLink>
                </PaginationItem>
                <PaginationItem>
                    <PaginationLink href="#">
                        2
                    </PaginationLink>
                </PaginationItem>
                <PaginationItem>
                    <PaginationLink href="#">
                        3
                    </PaginationLink>
                </PaginationItem>
                <PaginationItem>
                    <PaginationLink
                        href="#"
                        next
                    />
                </PaginationItem>
                <PaginationItem>
                    <PaginationLink
                        href="#"
                        last
                    />
                </PaginationItem>
            </Pagination>
        </div>
        </>
    );
}

export default PaginationTable;