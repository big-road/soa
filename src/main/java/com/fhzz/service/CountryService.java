/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.fhzz.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhzz.mapper.CountryMapper;
import com.fhzz.model.Country;
import com.github.pagehelper.PageHelper;

/**
 * @author liuzh
 * @since 2015-12-19 11:09
 */
@Service
public class CountryService {

    @Autowired
    private CountryMapper countryMapper;

    public List<Country> getAll(Integer page,Integer rows,String name,String code) {
        if (page != null && rows != null) {
            PageHelper.startPage(page, rows, "id");
        }
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("countryname", name);
        params.put("countrycode", code);
        return countryMapper.listCountry(params);
    }

    public Country getById(Integer id) {
        return countryMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        countryMapper.deleteByPrimaryKey(id);
    }

    public void save(Country country) {
        if (country.getId() != null) {
            countryMapper.updateByPrimaryKey(country);
        } else {
            countryMapper.insert(country);
        }
    }
}