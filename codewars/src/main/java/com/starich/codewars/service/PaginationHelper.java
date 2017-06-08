package com.starich.codewars.service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jason on 2017/5/24.
 */
// TODO: complete this object/class

public class PaginationHelper<I> {

    private List<I> collection;
    private int itemsPerPage;

    /**
     * The constructor takes in an array of items and a integer indicating how many
     * items fit within a single page
     */
    public PaginationHelper(List<I> collection, int itemsPerPage) {
            this.collection = collection;
            this.itemsPerPage = itemsPerPage;

    }

    /**
     * returns the number of items within the entire collection
     */
    public int itemCount() {
        return collection == null ? 0 : collection.size();
    }

    /**
     * returns the number of pages
     */
    public int pageCount() {
        if(itemCount() == 0){
            return 0;
        }else{
            return new BigDecimal(itemCount()).divide(new BigDecimal(itemsPerPage), BigDecimal.ROUND_CEILING).intValue();
        }
    }

    /**
     * returns the number of items on the current page. page_index is zero based.
     * this method should return -1 for pageIndex values that are out of range
     */
    public int pageItemCount(int pageIndex) {
        if(pageIndex < 0 || pageIndex > (pageCount() - 1)){
            return -1;
        }else{
            if(pageIndex < (pageCount() - 1)){
                return this.itemsPerPage;
            }else{
                return itemCount() - this.itemsPerPage * (pageCount() - 1);
            }
        }
    }

    /**
     * determines what page an item is on. Zero based indexes
     * this method should return -1 for itemIndex values that are out of range
     */
    public int pageIndex(int itemIndex) {
        if(itemCount() == 0){
            return -1;
        }
        if(itemIndex < 0 || itemIndex >= this.itemCount()){
            return -1;
        }
        return itemIndex / this.itemsPerPage;
    }
}
